console.log("Welcome to the Community Portal");

window.onload = function () {
  alert("Page loaded!");
  loadPreferences();
};

function countChars() {
  const feedback = document.getElementById("feedback");
  document.getElementById("charCount").textContent = "Characters: " + feedback.value.length;
}

function showFee(select) {
  const fee = select.value === "workshop" ? "₹500" : "₹300";
  document.getElementById("fee").textContent = "Fee: " + fee;
}

function validatePhone(input) {
  if (!/^\d{10}$/.test(input.value)) {
    alert("Please enter a valid 10-digit phone number.");
  }
}

function enlargeImage(img) {
  img.style.width = "300px";
}

function videoReady() {
  alert("Video ready to play!");
}

function findEvents() {
  const output = document.getElementById("geoOutput");
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(
      (position) => {
        output.textContent = \`Latitude: \${position.coords.latitude}, Longitude: \${position.coords.longitude}\`;
      },
      (error) => {
        output.textContent = \`Error: \${error.message}\`;
      },
      {
        enableHighAccuracy: true,
        timeout: 10000,
        maximumAge: 0
      }
    );
  } else {
    output.textContent = "Geolocation is not supported.";
  }
}

function clearPreferences() {
  localStorage.clear();
  sessionStorage.clear();
  alert("Preferences cleared!");
}

function loadPreferences() {
  const preferred = localStorage.getItem("eventType");
  if (preferred) {
    document.getElementById("eventType").value = preferred;
  }
}

document.getElementById("registerForm").addEventListener("submit", function (e) {
  e.preventDefault();
  localStorage.setItem("eventType", document.getElementById("eventType").value);
  document.getElementById("output").textContent = "Registration submitted!";
});