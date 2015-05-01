/**
 * Created by Asterios Raptis.
 */
jQuery.extend({
  getCurrentDateTime: function () {
    var now = new Date();
    var day = $.normalizeTimeUnit(now.getDate());
    var month = $.normalizeTimeUnit(now.getMonth() + 1);
    var fullYear = now.getFullYear();
    var hours = $.normalizeTimeUnit(now.getHours());
    var minutes = $.normalizeTimeUnit(now.getMinutes());
    var seconds = $.normalizeTimeUnit(now.getSeconds());
    now = day + '.' + month + '.' + fullYear + ' ' + hours + ':' + minutes + ':' + seconds;
    return now;
  },

  normalizeTimeUnit: function (timeunit) {
    if (timeunit < 10) {
      return "0" + timeunit;
    }
    return timeunit;
  },

  setCurrentDateTime: function (field) {
    field.text($.getCurrentDateTime());
  }

});



