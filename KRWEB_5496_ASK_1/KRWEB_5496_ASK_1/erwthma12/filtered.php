<html>
<head>
<link rel="stylesheet" type="text/css" href="style2.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<?php

$schedule = simplexml_load_file('test.xml');
if ( $_SERVER['REQUEST_METHOD'] == 'POST' ) {
echo '<div id="divaki2">'; 
echo '<table border="1">
      <tr bgcolor="#d8d8d8">
        <th>Title</th>
        <th>Professor</th>
		<th>Day</th>
		<th>Time</th>
		<th>Classroom</th>
      </tr>' ;
	  
foreach ($schedule->Lesson as $lesson) {
	if ($lesson->Lecture->Day==$_POST["filter"])
   echo '<tr>' ,'<td>' ,$lesson->Title, '</td>','<td>' ,$lesson->Professor, '</td>','<td>' ,$lesson->Lecture->Day, '</td>','<td>' ,$lesson->Lecture->Time, '</td>','<td>' ,$lesson->Lecture['Classroom'], '</td>','</tr>' ;
}
echo '</table>';
echo '</div>';
}



?>
</div>
</body>
</html> 