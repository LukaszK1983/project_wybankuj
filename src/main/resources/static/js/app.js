// document.addEventListener('DOMContentLoaded', function () {
//
//     var table = document.querySelector('table');
//     var delLinks = document.querySelectorAll('#remove');
//
//     for (var i = 0; i < delLinks.length; i++) {
//         delLinks[i].addEventListener('click', function (e) {
//             e.preventDefault();
//
//             var newTd = document.createElement('td');
//             var newLink = document.createElement('a');
//
//             // newLink.innerText = 'Potwierdzasz?';
//             newLink.classList.add('btn');
//             newLink.classList.add('btn-sm');
//             newLink.classList.add('btn-outline-danger');
//             newLink.classList.add('rounded');
//             newTd.innerHTML = '<a onClick=\"location.href=\'' + $(this).attr("href") + '\'\" class="btn btn-sm btn-outline-danger rounded">TAK</a> <a>NIE</a>';
//         // <a href="/bank/confirm?id=${bank.id}" class="btn btn-sm btn-outline-danger rounded">
//         //     newTd.appendChild(newLink);
//
//             var toAdd = document.querySelector('#remove');
//             toAdd.parentElement.appendChild(newTd);
//
//             console.log('Wiersz: ', toAdd);
//         });
//     }
// });

// document.addEventListener('DOMContentLoaded', function () {
//     const buttons = document.querySelectorAll('#remove');
//
//     for (let i = 0; i < buttons.length; i++) {
//         buttons[i].addEventListener('click', function (e) {
//             if (confirm('Na pewno usunąć?') === false) {
//                 e.preventDefault();
//                 return false;
//             }
//         });
//     }
// });

// $(document).ready(function () {
//     const buttons = $('#remove');
//
//     buttons.on('click', function (e) {
//         e.preventDefault();
//         const accept = $('#accept');
//         accept.show();
//         const reject = $('#reject');
//         reject.on('click', function () {
//             accept.hide();
//         });
//     });
// });

// document.addEventListener('DOMContentLoaded', function () {
//     const buttons = document.querySelectorAll('#remove');
//
//     for (const btn of buttons) {
//         btn.addEventListener('click', function (e) {
//             e.preventDefault();
//             const accept = document.querySelector('#accept');
//             accept.style.display = 'block';
//             accept.show();
//             const reject = document.querySelector('#reject');
//             reject.addEventListener('click', function () {
//                 accept.hide();
//             });
//         });
//     }
// });

document.addEventListener('DOMContentLoaded', function () {
    const buttons = document.querySelectorAll('#remove');

    for (const btn of buttons) {
        btn.addEventListener('click', function (e) {
            e.preventDefault();
            const headaction = document.querySelector('#headaction');
            headaction.innerHTML = 'Potwierdź usunięcie';
            const col = document.createElement('td');
            col.innerHTML = '<td><a href="' + $(this).attr("href") + '" class="btn btn-sm btn-outline-danger rounded">TAK</a> <a href="" class="btn btn-sm btn-outline-success rounded">NIE</a></td>';
            const row = document.querySelector('#rows');
            row.appendChild(col);
        });
    }
});


// $(document).ready(function(){
//     var delLinks = $('#remove');
//     console.log('Linki: ', delLinks);
//
//     for (var i = 0; i < delLinks.length; i++) {
//         $("#remove").click(function(){
//             $("#confirm")
//                 .html('<td><td style="text-align: center"><p> <a class="btn btn-sm btn-outline-danger rounded" onClick=\"location.href=\'' + $(this).attr("href") + '\'\"> TAK </a> <a class="btn btn-sm btn-outline-success rounded"> NIE </a></p></td></td>');
//             return false;
//         });
//         $("#confirm").click(function(){$(this).fadeOut();});
//     }
// });