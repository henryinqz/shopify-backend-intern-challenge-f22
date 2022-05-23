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

function onInventorySubmitUpdate() {
    const id = document.querySelector("#updateInventoryEntryId").value
    const updatedName = document.querySelector("#updateInventoryEntryName").value
    const updatedQuantity = document.querySelector("#updateInventoryEntryQuantity").value
    const updatedLocationId = document.querySelector("#updateInventoryEntryLocationId").value

    fetch(`${BASE_INVENTORY_ENDPOINT}/${id}`, {
        method: "PUT",
        headers: {"content-type": "application/json"},
        body: JSON.stringify({
            name: updatedName,
            quantity: updatedQuantity,
            locationId: updatedLocationId
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