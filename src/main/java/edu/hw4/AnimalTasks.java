package edu.hw4;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AnimalTasks {

    private AnimalTasks() {
    }

    public static List<Animal> sortAnimalsByHeight(List<Animal> animals) { // Задача 1
        return animals.stream()
            .sorted((a1, a2) -> Integer.compare(a1.height(), a2.height()))
            .collect(Collectors.toList());
    }

    public static List<Animal> sortAnimalsByWeightAndSelectTopK(List<Animal> animals, int k) { // Задача 2
        return animals.stream()
            .sorted((a1, a2) -> Integer.compare(a2.weight(), a1.weight()))
            .limit(k)
            .collect(Collectors.toList());
    }

    public static Map<Animal.Type, Integer> countAnimalsByType(List<Animal> animals) { // Задача 3
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.reducing(0, e -> 1, Integer::sum)));
    }

    public static Animal getAnimalWithLongestName(List<Animal> animals) { // Задача 4
        return animals.stream()
            .max(Comparator.comparingInt(a -> a.name().length()))
            .orElseThrow(NoSuchElementException::new);
    }

    public static Animal.Sex getMoreFrequentSex(List<Animal> animals) { // Задача 5
        Map<Animal.Sex, Long> sexCount = animals.stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()));
        return sexCount.get(Animal.Sex.M) > sexCount.get(Animal.Sex.F) ? Animal.Sex.M : Animal.Sex.F;
    }

    public static Map<Animal.Type, Animal> getHeaviestAnimalByType(List<Animal> animals) { // Задача 6
        return animals.stream()
            .collect(Collectors.toMap(
                Animal::type,
                Function.identity(),
                BinaryOperator.maxBy(Comparator.comparingInt(Animal::weight))
            ));
    }

    public static Animal getKthOldestAnimal(List<Animal> animals, int k) { // Задача 7
        return animals.stream()
            .sorted((a1, a2) -> Integer.compare(a2.age(), a1.age()))
            .skip(k - 1)
            .findFirst()
            .orElseThrow(NoSuchElementException::new);
    }

    public static Optional<Animal> getHeaviestAnimalBelowHeight(List<Animal> animals, int height) { // Задача 8
        return animals.stream()
            .filter(animal -> animal.height() < height)
            .max(Comparator.comparingInt(Animal::weight));
    }

    public static int getTotalNumberOfPaws(List<Animal> animals) { // Задача 9
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    public static List<Animal> getAnimalsWithMismatchedAgeAndPaws(List<Animal> animals) { // Задача 10
        return animals.stream()
            .filter(animal -> animal.age() != animal.paws())
            .collect(Collectors.toList());
    }

    public static List<Animal> getBitingAnimalsAboveHeight(List<Animal> animals, int height) { // Задача 11
        return animals.stream()
            .filter(animal -> animal.bites() && animal.height() > height)
            .toList(); // Внес изменение: Используем .toList() вместо collect(Collectors.toList())
    }

    public static long countAnimalsWithWeightGreaterThanHeight(List<Animal> animals) { // Задача 12
        return animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count();
    }

    public static List<Animal> getAnimalsWithNamesMoreThanTwoWords(List<Animal> animals) { // Задача 13
        return animals.stream()
            .filter(animal -> animal.name().split("\\s+").length > 2)
            .collect(Collectors.toList());
    }

    public static boolean isThereADogTallerThan(List<Animal> animals, int height) { // Задача 14
        return animals.stream()
            .anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > height);
    }

    public static Map<Animal.Type, Integer> getWeightByTypeForAgeRange(
        List<Animal> animals,
        int k,
        int l
    ) { // Задача 15
        // Задача 15
        return animals.stream()
            .filter(animal -> animal.age() >= k && animal.age() <= l)
            .collect(Collectors.groupingBy(
                Animal::type,
                Collectors.summingInt(Animal::weight)
            ));
    }

    public static List<Animal> sortAnimals(List<Animal> animals) { // Задача 16
        return animals.stream()
            .sorted(Comparator.comparing((Animal a) -> a.type().name())
                .thenComparing(a -> a.sex().name())
                .thenComparing(Animal::name))
            .collect(Collectors.toList());
    }

    public static boolean doSpidersBiteMoreOftenThanDogs(List<Animal> animals) { // Задача 17
        long spiderBites = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.SPIDER && animal.bites())
            .count();
        long dogBites = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG && animal.bites())
            .count();
        return spiderBites > dogBites;
    }

    public static Animal findHeaviestFishInLists(List<List<Animal>> listOfLists) { // Задача 18
        return listOfLists.stream()
            .flatMap(List::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight))
            .orElse(null);
    }

    public static Map<String, Set<ValidationError>> findAnimalsWithErrors(List<Animal> animals) { // Задача 19
        Map<String, Set<ValidationError>> errors = new HashMap<>();
        for (Animal animal : animals) {
            Set<ValidationError> animalErrors = new HashSet<>();
            if (animal.age() < 0) {
                animalErrors.add(ValidationError.NEGATIVE_AGE);
            }
            if (animal.height() < 0) {
                animalErrors.add(ValidationError.NEGATIVE_HEIGHT);
            }
            if (animal.weight() < 0) {
                animalErrors.add(ValidationError.NEGATIVE_WEIGHT);
            }
            if (!animalErrors.isEmpty()) {
                errors.put(animal.name(), animalErrors);
            }
        }
        return errors;
    }

    public enum ValidationError {
        NEGATIVE_AGE("Negative Age"),
        NEGATIVE_HEIGHT("Negative Height"),
        NEGATIVE_WEIGHT("Negative Weight");

        private final String message;

        ValidationError(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return message;
        }
    }

    public static Map<String, String> formatAnimalErrors(Map<String, Set<ValidationError>> errors) { // Задача 20
        return errors.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue().stream()
                    .sorted(Comparator.comparing(Enum::name))
                    .map(ValidationError::toString)
                    .collect(Collectors.joining(", ")),
                (e1, e2) -> e1,
                LinkedHashMap::new
            ));
    }
}
