document.addEventListener("DOMContentLoaded", function() {
    // Тут можно добавить функционал для динамических действий, например, подтверждения удаления
    document.querySelectorAll('.delete-button').forEach(button => {
        button.addEventListener('click', function(event) {
            if (!confirm('Вы уверены, что хотите удалить этот элемент?')) {
                event.preventDefault();
            }
        });
    });
});
