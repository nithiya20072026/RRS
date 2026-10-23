"use strict";

const TOTAL_SEATS = 3;

// Confirmed reservations: array representing a linked-list-style collection
const reservations = [];

// Waiting passengers: FIFO queue
const waitingQueue = [];

let nextPassengerId = 1;

const bookingForm = document.getElementById("bookingForm");
const cancelForm = document.getElementById("cancelForm");
const message = document.getElementById("message");

function showMessage(text, type = "info") {
    message.textContent = text;
    message.className = `message ${type}`;
}

function createPassenger(name, age, trainName) {
    return {
        id: nextPassengerId++,
        name,
        age,
        trainName
    };
}

// Book a ticket or add the passenger to the waiting queue
bookingForm.addEventListener("submit", function (event) {
    event.preventDefault();

    const name = document.getElementById("passengerName")
        .value.trim();

    const age = Number(
        document.getElementById("passengerAge").value
    );

    const trainName = document.getElementById("trainName")
        .value.trim();

    if (!name || !trainName) {
        showMessage("Please enter all passenger details.", "error");
        return;
    }

    if (!Number.isInteger(age) || age < 1 || age > 120) {
        showMessage("Please enter a valid age between 1 and 120.", "error");
        return;
    }

    const passenger = createPassenger(name, age, trainName);

    if (reservations.length < TOTAL_SEATS) {
        reservations.push(passenger);

        showMessage(
            `Ticket confirmed for ${passenger.name}. Passenger ID: ${passenger.id}.`,
            "success"
        );
    } else {
        waitingQueue.push(passenger);

        showMessage(
            `${passenger.name} added to the waiting list. Passenger ID: ${passenger.id}.`,
            "info"
        );
    }

    bookingForm.reset();
    renderAll();
});

// Cancel a confirmed reservation or waiting-list entry
cancelForm.addEventListener("submit", function (event) {
    event.preventDefault();

    const id = Number(
        document.getElementById("cancelId").value
    );

    if (!Number.isInteger(id) || id < 1) {
        showMessage("Please enter a valid passenger ID.", "error");
        return;
    }

    const reservationIndex = reservations.findIndex(
        passenger => passenger.id === id
    );

    if (reservationIndex !== -1) {
        const cancelled = reservations.splice(
            reservationIndex, 1
        )[0];

        let result = `Reservation cancelled for ${cancelled.name}.`;

        // Promote the first passenger in the FIFO queue
        if (waitingQueue.length > 0) {
            const promoted = waitingQueue.shift();
            reservations.push(promoted);

            result += ` ${promoted.name} (ID: ${promoted.id}) has received the available seat.`;
        }

        showMessage(result, "success");
        cancelForm.reset();
        renderAll();
        return;
    }

    const waitingIndex = waitingQueue.findIndex(
        passenger => passenger.id === id
    );

    if (waitingIndex !== -1) {
        const removed = waitingQueue.splice(waitingIndex, 1)[0];

        showMessage(
            `Waiting-list entry cancelled for ${removed.name}.`,
            "success"
        );

        cancelForm.reset();
        renderAll();
        return;
    }

    showMessage("Passenger ID not found.", "error");
});

// Create passenger cards without inserting user input as HTML
function createPassengerCard(passenger, status, position = null) {
    const card = document.createElement("article");
    card.className = "passenger-card";

    const details = document.createElement("div");
    details.className = "passenger-details";

    const heading = document.createElement("h3");
    heading.textContent = `${passenger.name} (ID: ${passenger.id})`;

    const age = document.createElement("p");
    age.textContent = `Age: ${passenger.age}`;

    const train = document.createElement("p");
    train.textContent = `Train: ${passenger.trainName}`;

    details.append(heading, age, train);

    const statusLabel = document.createElement("span");
    statusLabel.className = "passenger-status " +
        (status === "Confirmed" ? "status-confirmed" : "status-waiting");

    statusLabel.textContent = position === null
        ? status
        : `Waiting #${position}`;

    card.append(details, statusLabel);

    return card;
}

function renderPassengerList(containerId, passengers, status) {
    const container = document.getElementById(containerId);
    container.replaceChildren();

    if (passengers.length === 0) {
        const empty = document.createElement("p");
        empty.className = "empty";
        empty.textContent = status === "Confirmed"
            ? "No confirmed reservations yet."
            : "Waiting list is empty.";

        container.appendChild(empty);
        return;
    }

    passengers.forEach((passenger, index) => {
        const position = status === "Waiting"
            ? index + 1
            : null;

        container.appendChild(
            createPassengerCard(passenger, status, position)
        );
    });
}

// Update passenger lists, statistics, and availability
function renderAll() {
    renderPassengerList(
        "reservationsList",
        reservations,
        "Confirmed"
    );

    renderPassengerList(
        "waitingList",
        waitingQueue,
        "Waiting"
    );

    const availableSeats = TOTAL_SEATS - reservations.length;

    document.getElementById("totalSeats").textContent =
        TOTAL_SEATS;

    document.getElementById("bookedSeats").textContent =
        reservations.length;

    document.getElementById("availableSeats").textContent =
        availableSeats;

    document.getElementById("waitingCount").textContent =
        waitingQueue.length;

    const percentageAvailable =
        (availableSeats / TOTAL_SEATS) * 100;

    const progress = document.getElementById("seatProgress");
    progress.style.width = `${percentageAvailable}%`;

    progress.style.background = availableSeats === 0
        ? "#dc2626"
        : availableSeats === TOTAL_SEATS
            ? "#16a34a"
            : "#2563eb";

    document.getElementById("availabilityText").textContent =
        `${availableSeats} out of ${TOTAL_SEATS} seats available.`;
}

// Display the initial state
renderAll();