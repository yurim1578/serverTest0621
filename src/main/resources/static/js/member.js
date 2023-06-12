// Main
let container = document.getElementById('container')

toggle = () => {
  container.classList.toggle('sign-in')
  container.classList.toggle('sign-up')
}

setTimeout(() => {
  container.classList.add('sign-in')
}, 200)

// Modal
function openModal() {
  var modal = document.getElementById("myModal");
  modal.style.display = "block";
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

// 유효성검사 Example starter JavaScript for disabling form submissions if there are invalid fields
(function () {
  'use strict'

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  var forms = document.querySelectorAll('.needs-validation')

  // Loop over them and prevent submission
  Array.prototype.slice.call(forms)
    .forEach(function (form) {
      form.addEventListener('submit', function (event) {
        if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
        }

        form.classList.add('was-validated')
      }, false)
    })
})()

// phoneNum 입력할 때 자동하이픈 들어가게하기
var autoHypenPhone = function(str) {
  str = str.replace(/[^0-9]/g, '');
  var tmp = '';
  if (str.length < 4) {
    return str;
  } else if (str.length < 7) {
    tmp += str.substr(0, 3);
    tmp += '-';
    tmp += str.substr(3);
    return tmp;
  } else if (str.length < 11) {
    tmp += str.substr(0, 3);
    tmp += '-';
    tmp += str.substr(3, 3);
    tmp += '-';
    tmp += str.substr(6);
    return tmp;
  } else {
    tmp += str.substr(0, 3);
    tmp += '-';
    tmp += str.substr(3, 4);
    tmp += '-';
    tmp += str.substr(7);
    return tmp;
  }
}

var phoneNum = document.getElementById('phoneNum');

phoneNum.onkeyup = function() {
  console.log(this.value);
  this.value = autoHypenPhone(this.value);
}






/*// 회원가입
let index = {
  init: function() {
    $("#btn-save").on("click", () => {
      this.save();
    });
  },
  save: function() {
    let data = {
      username: $("#username").val(),
      password: $("#password").val(),
      email: $("#email").val()
    };

    $.ajax({
      type: "POST",
      url: "/auth/joinProc",
      data: JSON.stringify(data),
      contentType: "application/json; charset=utf-8",
      dataType: "json"
    }).done(function(resp) {
      if (resp.status === 500) {
        alert("회원가입에 실패하였습니다.");
      } else {
        alert("회원가입이 완료되었습니다.");
        location.href = "/";
      }
    }).fail(function(error) {
      alert(JSON.stringify(error));
    });
  }
};

index.init();*/



/*
Table: member
Columns:
member_id varchar(255) PK
member_name varchar(255)
created_date datetime(6)
modified_date datetime(6)
member_addr1 varchar(255)
member_addr2 varchar(255)
member_auth varchar(255)
member_email varchar(255)
member_phone varchar(255)
member_pw varchar(255)
member_zip_code varchar(255)*/


/*			        Username : $("#member_name").val(),
                    Id : $("#member_name").val(),
                    address : $("#member_addr1").val(),
                    phoneNum : $("#member_phon").val(),
                    Email :$("#member_email").val()
                    Password : $("#member_pw").val(),
                    Confirm password : $("#member_pw").val(),*/

