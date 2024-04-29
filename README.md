# Import

## Maven
```xml
<repositories>
    <repository>
        <id>github</id>
        <name>Maven GitHub Repository</name>
        <url>https://maven.pkg.github.com/DverkaSK/chances</url>
    </repository>
</repositories>
```

```xml
<dependency>
    <groupId>ru.dverkask</groupId>
    <artifactId>chanceapi-java</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

## Gradle

```groovy
repositories {
    maven { url = "https://maven.pkg.github.com/DverkaSK/chances" }
}
```
```groovy
dependencies {
    compileOnly "ru.dverkask:chanceapi-java:1.0-SNAPSHOT"
}
```

# Usage

## Create ChanceTable
```java
ChanceTable<Object> table = ChanceTable.create(ChanceType.WEIGHT,
            new Chance<>(new Object(), 15),
            new Chance<>(new Object(), 20),
            new Chance<>(new Object(), 25),
            new Chance<>(new Object(), 30)
        );
```

## Roll random object from table

```java
for (int i = 0; i < 10; i++) {
    table.roll();
}
```

## Create category
```java
Category<Object> category = new Category<>("customCategory", 50);
category.addItem(new Object());
category.addItem(new Object());
category.addItem(new Object());
        
table.addCategory(category);
```