package Census;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main ( String[] args ) {
        List<String> names = Arrays.asList ( "Jack" , "Connor" , "Harry" , "George" , "Samuel" , "John" );
        List<String> families = Arrays.asList ( "Evans" , "Young" , "Harris" , "Wilson" , "Davies" , "Adamson" , "Brown" );
        List<Person> personList = new ArrayList<> ( );
        for (int i = 0; i < 10_000_000; i++) {
            personList.add ( new Person (
                    names.get ( new Random ( ).nextInt ( names.size ( ) ) ) ,
                    families.get ( new Random ( ).nextInt ( families.size ( ) ) ) ,
                    new Random ( ).nextInt ( 100 ) ,
                    Sex.values ( )[new Random ( ).nextInt ( Sex.values ( ).length )] ,
                    Education.values ( )[new Random ( ).nextInt ( Education.values ( ).length )] )
            );
        }

        long underage = personList.stream ( )
                .filter ( x -> x.getAge ( ) < 18 )
                .count ( );
        System.out.println ( "1.Количество несовершеннолетних: " + underage );

        List<String> draftList = personList.stream ( )
                .filter ( x -> x.getAge ( ) >= 18 && x.getAge ( ) < 27 && x.getSex ( ).equals ( Sex.MAN ) )
                .map ( Person::getFamily )
                .limit ( 10 ) // вывод часть списка на экран
                .collect ( Collectors.toList ( ) );
        System.out.println ( "2.Список фамилий призывников: " + draftList );

        List<Person> higherEducation = personList.stream ( )
                .filter ( x -> x.getAge ( ) >= 18 )
                .filter ( x -> x.getSex ( ).equals ( Sex.WOMAN ) && x.getAge ( ) < 60 ||
                        x.getSex ( ).equals ( Sex.MAN ) && x.getAge ( ) < 65 )
                .filter ( x -> x.getEducation ( ).equals ( Education.HIGHER ) )
                .limit ( 10 )
                .sorted ( Comparator.comparing ( Person::getFamily ) )
                .collect ( Collectors.toList ( ) );
        System.out.println ( "3.Потенциально работоспособные люди с высшим образованием: " + higherEducation );
    }
}


