function formatJson(text) {
    try {
        return JSON.stringify(JSON.parse(text), null, 2);
    } catch (e) {
        return text;
    }
}

async function callApi(method, path, bodyText) {
    var output = document.getElementById("responseOutput");
    var meta = document.getElementById("responseMeta");

    if (!path || path.trim().length === 0) {
        meta.textContent = "Please provide a request path.";
        return;
    }

    var url = window.location.origin + path.trim();
    var options = {
        method: method,
        headers: {
            "Accept": "application/json"
        }
    };

    if (method !== "GET" && bodyText.trim().length > 0) {
        options.headers["Content-Type"] = "application/json";
        options.body = bodyText;
    }

    meta.textContent = "Loading " + method + " " + path + " ...";

    try {
        var response = await fetch(url, options);
        var raw = await response.text();
        output.textContent = formatJson(raw);
        meta.textContent = method + " " + path + " -> " + response.status + " " + response.statusText;
    } catch (error) {
        output.textContent = String(error);
        meta.textContent = method + " " + path + " -> request failed";
    }
}

(function initApiLab() {
    var sendBtn = document.getElementById("sendBtn");
    var clearBtn = document.getElementById("clearBtn");

    sendBtn.addEventListener("click", function () {
        var method = document.getElementById("method").value;
        var path = document.getElementById("path").value;
        var body = document.getElementById("body").value;
        callApi(method, path, body);
    });

    clearBtn.addEventListener("click", function () {
        document.getElementById("responseMeta").textContent = "No request yet.";
        document.getElementById("responseOutput").textContent = "Run a request to see response here.";
    });
})();
