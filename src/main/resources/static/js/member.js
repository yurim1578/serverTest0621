// Main
let container = document.getElementById('container');

function redirectToSignUpAndToggle() {
  toggle();
  window.location.href = "/signup";
}

function toggle() {
  container.classList.toggle('sign-in');
  container.classList.toggle('sign-up');
}

setTimeout(() => {
  container.classList.add('sign-in');
}, 200);

// Form validation
(function () {
  'use strict';

  var forms = document.querySelectorAll('.needs-validation');

  Array.prototype.slice.call(forms).forEach(function (form) {
    form.addEventListener('submit', function (event) {
      if (!form.checkValidity()) {
        event.preventDefault();
        event.stopPropagation();
      }

      form.classList.add('was-validated');
    });
  });
})();





/*///* Modal
function openModal() {

}

function closeModal() {
  var modal = document.getElementById("myModal");
  modal.style.display = "none";
}

window.onclick = function(event) {
  var modal = document.getElementById("myModal");
  if (event.target === modal) {
    modal.style.display = "none";
  }
}
*/