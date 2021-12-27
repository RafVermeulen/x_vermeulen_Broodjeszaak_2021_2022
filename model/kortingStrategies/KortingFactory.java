package model.kortingStrategies;

public class KortingFactory {
    public static Korting setKorting(KortingType type){
        Korting instance = null;
        String className = type.getClassName();

        try {
            instance = (Korting) Class.forName(className).getConstructor().newInstance();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.exit(500);
        }
        return instance;
    }

    public static Korting setKorting(String kortingName) {
        KortingType type = KortingType.findByName(kortingName);
        return setKorting(type);
    }
}
