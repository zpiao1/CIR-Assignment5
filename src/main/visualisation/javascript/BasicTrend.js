tester = document.getElementById('tester');

data = [{
		x: [1, 2, 3, 4, 5],
		y: [1, 2, 4, 8, 16] 
		}]
		
title: 'Basic Trend Plot'
Plotly.plot( tester, data, {margin: { t: 0 } } );