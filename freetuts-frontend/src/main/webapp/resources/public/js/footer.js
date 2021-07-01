$(document).ready(function () {
  $(".menu-click").click(function () {
    if ($(this).hasClass("active")) {
      $(this).removeClass("active");
      $(".func-item").hide(700);
    } else {
      $(".menu-click").removeClass("active");
      var id = $(this).attr("id");
      $(".func-item").hide(700);

      $("#" + id + "-data").show(500);

      $(this).addClass("active");
      if ("totop" == $(this).attr("id")) {
        $(this).removeClass("active");
      }
    }
  });

  // this is the id of the form
  $("#error-contact").submit(function (e) {
    e.preventDefault(); // avoid to execute the actual submit of the form.

    var form = $(this);
    var url = form.attr("action");

    $.ajax({
      type: "POST",
      url: url,
      data: form.serialize(), // serializes the form's elements.
      success: function (data) {
        $("#error_remove").css("display", "none");
        $("#error_success").css("display", "block");
        console.log(data); // show response from the php script.
      },
    });
  });
});
