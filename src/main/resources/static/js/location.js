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

function onLocationSubmitUpdate() {
    const id = document.querySelector("#updateLocationId").value
    const updatedName = document.querySelector("#updateLocationName").value
    const updatedAddress = document.querySelector("#updateLocationAddress").value
    const updatedZipCode = document.querySelector("#updateLocationZipCode").value
    const updatedCity = document.querySelector("#updateLocationCity").value
    const updatedState = document.querySelector("#updateLocationState").value
    const updatedCountry = document.querySelector("#updateLocationCountry").value

    fetch(`${BASE_LOCATIONS_ENDPOINT}/${id}`, {
        method: "PUT",
        headers: {"content-type": "application/json"},
        body: JSON.stringify({
            name: updatedName,
            address: updatedAddress,
            zipCode: updatedZipCode,
            city: updatedCity,
            state: updatedState,
            country: updatedCountry
        })
    })
        .then(response => response.json())
        .then(() => window.location.reload())
}

function onLocationDelete(id) {
    fetch(`${BASE_LOCATIONS_ENDPOINT}/${id}`, {
        method: "DELETE"
    })
        .then(response => response.json())
        .then(() => window.location.reload())
}