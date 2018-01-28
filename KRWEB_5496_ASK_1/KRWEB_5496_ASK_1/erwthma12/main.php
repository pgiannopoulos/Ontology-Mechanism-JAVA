<html>
<head>
<link rel="stylesheet" type="text/css" href="style2.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<?php

$schedule = simplexml_load_file('test.xml');
if ( $_SERVER['REQUEST_METHOD'] == 'POST' ) {

$lesson = $schedule->addChild('Lesson');
$lesson->addChild('Title', $_POST["title"]);
$lesson->addChild('Professor', $_POST["professor"]);

$lecture = $lesson->addChild('Lecture');
$lecture->addChild('Day', $_POST["day"]);
$lecture->addChild('Time', $_POST["time"]);
$lecture->addAttribute('Classroom', $_POST["classroom"]);

echo $schedule->asXML('test.xml');
}
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
	if ($lesson == $schedule->Lesson[0]) {
		echo '<tr>' ,'<td>' ,$lesson->Title, '</td>','<td>' ,$lesson->Professor, '</td>','<td>' ,$lesson->Lecture[0]->Day, '</td>','<td>' ,$lesson->Lecture[0]->Time, '</td>','<td>' ,$lesson->Lecture[0]['Classroom'], '</td>','</tr>' ;
		echo '<tr>' ,'<td>' ,$lesson->Title, '</td>','<td>' ,$lesson->Professor, '</td>','<td>' ,$lesson->Lecture[1]->Day, '</td>','<td>' ,$lesson->Lecture[1]->Time, '</td>','<td>' ,$lesson->Lecture[1]['Classroom'], '</td>','</tr>' ;
	}
	else{
   echo '<tr>' ,'<td>' ,$lesson->Title, '</td>','<td>' ,$lesson->Professor, '</td>','<td>' ,$lesson->Lecture->Day, '</td>','<td>' ,$lesson->Lecture->Time, '</td>','<td>' ,$lesson->Lecture['Classroom'], '</td>','</tr>' ;
	}
}
echo '</table>';
echo '</div>';
echo '<h1>Filter by day:</h1>';
echo'<form action="filtered.php" method="post">
  <select name="filter">
    <option value="Monday">Monday</option>
    <option value="Tuesday">Tuesday</option>
    <option value="Wednesday">Wednesday</option>
    <option value="Thursday">Thursday</option>
	<option value="Friday">Friday</option>
    <option value="Saturday">Saturday</option>
	<option value="Sunday">Sunday</option>
  </select>
  <input type="submit" value="Submit">
</form';
?>
</div>
</body>
</html> 
