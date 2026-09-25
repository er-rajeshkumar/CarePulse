async function runDoctorRequest(path) {
    var output = document.getElementById("responseOutput");
    var meta = document.getElementById("responseMeta");
    var url = window.location.origin + path;

    meta.textContent = "Loading GET " + path + " ...";

    try {
        var response = await fetch(url, {
            method: "GET",
            headers: {
                "Accept": "application/json"
            }
        });

        var raw = await response.text();
        try {
            output.textContent = JSON.stringify(JSON.parse(raw), null, 2);
        } catch (e) {
            output.textContent = raw;
        }

        meta.textContent = "GET " + path + " -> " + response.status + " " + response.statusText;
    } catch (error) {
        output.textContent = String(error);
        meta.textContent = "GET " + path + " -> request failed";
    }
}

(function initDoctorApiChecks() {
    var quickButtons = document.querySelectorAll("button[data-endpoint]");
    for (var i = 0; i < quickButtons.length; i++) {
        quickButtons[i].addEventListener("click", function (event) {
            var endpoint = event.currentTarget.getAttribute("data-endpoint");
            runDoctorRequest(endpoint);
        });
    }

    var byIdButton = document.getElementById("doctorByIdBtn");
    byIdButton.addEventListener("click", function () {
        var idValue = document.getElementById("doctorId").value;
        if (!idValue) {
            document.getElementById("responseMeta").textContent = "Please enter a doctor ID.";
            return;
        }
        runDoctorRequest("/carepulse/doctors/" + idValue);
    });
})();
