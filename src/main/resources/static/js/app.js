document.addEventListener('DOMContentLoaded', function () {
    const buttons = document.querySelectorAll('#remove');

    for (const btn of buttons) {
        btn.addEventListener('click', function (e) {
            e.preventDefault();
            const row = this.parentElement.parentElement;
            row.lastElementChild.innerHTML = 'Na pewno? <a href="' + $(this).attr("href") + '" class="btn btn-sm btn-outline-danger rounded">TAK</a> <a href="" class="btn btn-sm btn-outline-success rounded">NIE</a>';
        });
    }

    number.toLocaleString();
});