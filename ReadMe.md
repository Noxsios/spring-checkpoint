# Spring Checkpoint

#### Question 1 Example
```
curl -i "http://localhost:8080/camelize?original=this_is_a_thing"

curl -i "http://localhost:8080/camelize?original=this_is_a_thing&initialCap=true"
```
#### Question 2 Example
```
curl -i "http://localhost:8080/redact?original=A+little+of+this+and+a+little+of+that&badWord=little"
```
#### Question 3 Example
```
curl -i -X POST "http://localhost:8080/encode?message=a+little+of+this+and+a+little+of+that&key=mcxrstunopqabyzdefghijklvw"
```

#### Question 4 Example
