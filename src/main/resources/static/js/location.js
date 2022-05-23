const BASE_LOCATIONS_ENDPOINT = "/api/locations"

function onLocationSubmit() {
    const name = document.querySelector("#createLocationName").value
    const address = document.querySelector("#createLocationAddress").value
    const zipCode = document.querySelector("#createLocationZipCode").value
    const city = document.querySelector("#createLocationCity").value
    const state = document.querySelector("#createLocationState").value
    const country = document.querySelector("#createLocationCountry").value

    if (name !== '' && address !== '' && zipCode !== '' && city !== '' && state !== '' && country !== '') {
        fetch(`${BASE_LOCATIONS_ENDPOINT}/create`, {
            method: "POST",
            headers: {"content-type": "application/json"},
            body: JSON.stringify({
                name: name,
                address: address,
                zipCode: zipCode,
                city: city,
                state: state,
                country: country
            })
        })
            .then(response => response.json())
            .then(() => window.location.reload())
    }
}

function onLocationSubmitUpdate(id) {
    fetch(`${BASE_LOCATIONS_ENDPOINT}/${id}`, {
        method: "PUT",
        headers: {"content-type": "application/json"},
        body: JSON.stringify({
            name: "Updated location name!",
            address: "123 Happyjack Street",
            zipCode: "H0H0H0",
            city: "Markham",
            state: "ON",
            country: "Canada"
        })
    })
        .then(response => response.json())
        .then(data => console.log(data))
        .then(() => window.location.reload())
}

function onLocationDelete(id) {
    fetch(`${BASE_LOCATIONS_ENDPOINT}/${id}`, {
        method: "DELETE"
    })
        .then(response => response.json())
        .then(() => window.location.reload())
}