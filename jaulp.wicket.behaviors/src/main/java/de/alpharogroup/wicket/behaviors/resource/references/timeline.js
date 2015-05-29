google.load("visualization", "1", {packages:["timeline"]});
google.setOnLoadCallback(drawChart);

function drawChart() {
  var container = document.getElementById('timeline');
  var chart = new google.visualization.Timeline(container);
  var dataTable = new google.visualization.DataTable();

  dataTable.addColumn({ type: 'string', id: 'President' });
  dataTable.addColumn({ type: 'date', id: 'Start' });
  dataTable.addColumn({ type: 'date', id: 'End' });
  dataTable.addRows([
    [ 'Washington', new Date(1789, 3, 29), new Date(1797, 2, 3) ],
    [ 'Adams',      new Date(1797, 2, 3),  new Date(1801, 2, 3) ],
    [ 'Jefferson',  new Date(1801, 2, 3),  new Date(1809, 2, 3) ]
  ]);

  chart.draw(dataTable);
}
