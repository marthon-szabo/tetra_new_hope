function getButtonToSendLetters() {
    let button = document.querySelector('.button');
    button.addEventListener('click', function () {
        sendLettersToBackend(button.dataset.letter);
    })
}

function sendLettersToBackend(letter) {
    let letterToSend = {"letter" : `${letter}`};

    fetch('/', {

        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        credentials: 'same-origin',
        body: JSON.stringify(letterToSend)
    })
        .then((response) => response.json())
        .then((stored_letters) => console.log(stored_letters));
}

getButtonToSendLetters();
