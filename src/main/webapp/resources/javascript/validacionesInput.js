/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * Al Garulo, al Tuleño y al Richard les gusta doblada y desdoblada.
 * 
 */



$(document).ready(function () {
   $('.mayusculas input').keyup(function (){
       $(this).val($(this).val().toUpperCase());
   });
});

$(document).on("keyup", ".mayusculas input", function () {
//   console.log("inside";   <-- here it is
    $(this).val($(this).val().toUpperCase());
});










