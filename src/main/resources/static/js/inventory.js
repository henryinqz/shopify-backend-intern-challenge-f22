const BASE_INVENTORY_ENDPOINT = "/api/inventory"

function onInventorySubmit() {
    const name = document.querySelector("#createInventoryEntryName").value
    const quantity = document.querySelector("#createInventoryEntryQuantity").value
    const locationId = document.querySelector("#createInventoryEntryLocationId").value

    if (name !== '' && quantity !== '' && locationId !== '') {
        fetch(`${BASE_INVENTORY_ENDPOINT}/create`, {
            method: "POST",
            headers: {"content-type": "application/json"},
            body: JSON.stringify({
                name: name,
                quantity: parseInt(quantity),
                locationId: locationId
            })
        })
            .then(response => response.json())
            .then(() => window.location.reload())
    }
}

function onInventorySubmitUpdate(id) {
    fetch(`${BASE_INVENTORY_ENDPOINT}/${id}`, {
        method: "PUT",
        headers: {"content-type": "application/json"},
        body: JSON.stringify({
            name: "Updated Item!",
            quantity: 2,
            locationId: "628a868059a10309f7f35172"
        })
    })
        .then(response => response.json())
        .then(() => window.location.reload())
}

function onInventoryDelete(id) {
    fetch(`${BASE_INVENTORY_ENDPOINT}/${id}`, {
        method: "DELETE"
    })
        .then(response => response.json())
        .then(() => window.location.reload())
}