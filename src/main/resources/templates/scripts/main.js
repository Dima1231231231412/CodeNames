async function init() {
    const data = await fetchCards();
    wordBoard = data.word;
    colorBoard = data.color;
    for (let i = 0; i < cardsNum; i++) {
        cards[i].onclick = cardClick;
        cards[i].innerHTML = wordBoard[i];
        cards[i].classList.add(colorBoard[i]);
    }
    startTimer();
}

async function fetchCards() {
    return fetch('http://localhost:8080/cards')
    .then(response => response.json());
}

function cardClick(evt) {
    if (activeSpymaster == 1)
        return;
    if (cards[evt.target.id].classList[1] != "shown") {
        clearInterval(myInterval);
        startTimer();
        if (playingTeam == "r") {
            if (colorBoard[evt.target.id] == "r") {
                cards[evt.target.id].classList.add("shown");
                redNum--;
                redScore.innerHTML = `Осталось красных: ${redNum}`;
                if (redNum == 0)
                    endGame("r");
                return;
            }
            if (colorBoard[evt.target.id] == "b") {
                cards[evt.target.id].classList.add("shown");
                blueNum--;
                blueScore.innerHTML = `Осталось синих: ${blueNum}`;
                flipTurn();
                if (blueNum == 0)
                    endGame("b");
                return;
            }
            if (colorBoard[evt.target.id] == "n") {
                cards[evt.target.id].classList.add("shown");
                flipTurn();
                return;
            }
            if (colorBoard[evt.target.id] == "a") {
                cards[evt.target.id].classList.add("shown");
                endGame("b");
                return;
            }
        }
        if (colorBoard[evt.target.id] == "r") {
            cards[evt.target.id].classList.add("shown");
            redNum--;
            redScore.innerHTML = `Осталось красных: ${redNum}`;
            flipTurn();
            if (redNum == 0)
                endGame("r");
            return;
        }
        if (colorBoard[evt.target.id] == "b") {
            cards[evt.target.id].classList.add("shown");
            blueNum--;
            blueScore.innerHTML = `Осталось синих: ${blueNum}`;
            if (blueNum == 0)
                endGame("b");
            return;
        }
        if (colorBoard[evt.target.id] == "n") {
            cards[evt.target.id].classList.add("shown");
            flipTurn();
            return;
        }
        if (colorBoard[evt.target.id] == "a") {
            cards[evt.target.id].classList.add("shown");
            endGame("r");
        }
    }
}

function showSpymaster() {
    if (activeSpymaster == 0) {
        spymaster.innerHTML = "Показать игровую доску";
        activeSpymaster = 1;
        for (let i = 0; i < cardsNum; i++)
            cards[i].classList.toggle("spymaster");
        return;
    }
    spymaster.innerHTML = "Доска спаймастера";
    activeSpymaster = 0;
    for (let i = 0; i < cardsNum; i++)
        cards[i].classList.toggle("spymaster");
}

function newGame() {
    
}

function startTimer() {
    var timer = 60;
    window.myInterval = setInterval(function () {
        display.innerHTML = timer;
        if (--timer < 0) {
            flipTurn();
            timer = 60;
        }
    }, 1000);
}

function endGame(winner) {
    clearInterval(myInterval);
    for (let i = 0; i < cardsNum; i++) {
        cards[i].onclick = "";
    }
    if (winner == "r") {
        teamTurn.innerHTML = "Победитель: Красные";
        team.style.background = "red";
        return;
    }
    teamTurn.innerHTML = "Победитель: Синие";
    team.style.background = "blue";
}

function flipTurn() {
    if (playingTeam == "r") {
        playingTeam = "b";
        teamTurn.innerHTML = "Ход синих";
        team.style.background = "blue";
        return;
    }
    playingTeam = "r";
    teamTurn.innerHTML = "Ход красных";
    team.style.background = "red";
}

let colorBoard = [];
let wordBoard = [];
let activeSpymaster = 0;
const cardsNum = 25;
let redNum = 9;
let blueNum = 8;
let playingTeam = "r";
const grid = document.getElementsByClassName("grid-container")[0];
const cards = grid.children;
const spymaster = document.getElementById("spymaster-button");
const team = document.getElementsByClassName("team")[0];
const teamTurn = document.getElementById("team-turn");
const redScore = document.getElementById("red-score");
const blueScore = document.getElementById("blue-score");
const display = document.getElementById('timer');
spymaster.addEventListener('click', showSpymaster);
init();