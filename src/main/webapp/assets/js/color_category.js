document.addEventListener("DOMContentLoaded", function() {
    document.querySelectorAll(".card").forEach(card => {
        const color = card.dataset.color;
        const titles = card.querySelectorAll(".escola");
        titles.forEach(t => {
            t.style.color = color;
        });
    });
});
