Morris.Line({
      element: 'line-example',
      data: [
        { y: 'MD1', a: 58, b: 58, c:46, d:30, e:17 },
        { y: 'MD2', a: 0, b: 0, c: 0, d: 0, e: 0 },
        { y: 'MD3', a: 0, b: 0, c: 0, d: 0, e: 0 },
        { y: 'MD4', a: 0, b: 0, c: 0, d: 0, e: 0 },
        { y: 'MD5', a: 0, b: 0, c: 0, d: 0, e: 0 },
        { y: 'MD6', a: 0, b: 0, c: 0, d: 0, e: 0 }
      ],
        hoverCallback: function(index, options, content) {
            return(content);
        },
      xkey: 'y',
      ykeys: ['a', 'b', 'c', 'd', 'e'],
      labels: ['Dendooven', 'Wouter', 'Galle', 'Glenn', 'Stan'],
      parseTime: false,
      gridTextColor: ['#E8E8E8'],
      hideHover: 'auto',
      lineColors:['gray', 'indianred', 'orange', 'gold', 'lightgreen']
});
    
