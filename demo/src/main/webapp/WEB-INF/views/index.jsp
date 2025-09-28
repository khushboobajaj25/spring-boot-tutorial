<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Spring MVC Example</title>
</head>
<body>
    <h2>${message}</h2>
    <h3>Person Details 1:</h3>
    <ul>
        <li>Name: ${person1.name}</li>
        <li>Age: ${person1.age}</li>
    </ul>
    <h3>Person 2 Details:</h3>
    <ul>
        <li>Name: ${person2.name}</li>
        <li>Age: ${person2.age}</li>
    </ul>
    <h3>Person 3 Details:</h3>
    <ul>
        <li>Name: ${person3.name}</li>
        <li>Age: ${person3.age}</li>
    </ul>
</body>
</html>
