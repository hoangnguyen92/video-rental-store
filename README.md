#Decisions

* Create BaseModel which is parent of all entities so it cover everything common
 for every entity. For example: property id and hashCode() and equals()
* Create a CRUDRepository as parent of all repository it has all common methods for
CRUD operation 
* Create enum FilmType which include price,initial days and bonus points for each type
* Create additional entity Order which has:
	Film - the film which is rented, 
	expectedDays - expected days of rent,
	price - price based on expected days,
	surcharge - surcharge when the film is returned late,
	rentDate - day of renting,
	returnDate - day the film is returned
* Our main entity is Rental which has List of Order every film is in different Order. It has also
the customer who is renting so we can calculate the points
* About the unit test I just create 1 for packages: repositories and resources because I did not have time 
for other in those packages but it should be the same.
* more important unit tests are: RentalServiceTest, BonusPointCalculatorTest ,PriceCalculatorTest