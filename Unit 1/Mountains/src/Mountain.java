public class Mountain {
    private String rawData = "";
    private String[] data = new String[6];
    private double height = 0;

    public Mountain(String rawData) {
        this.rawData = rawData;
        String[] data = rawData.split("\\t");
       

        for (int i = 0; i < 6; i++) {
            this.data[i] = data[i];
        }
        //convert from format 1234 m to 1234 and 12,345 ft to m
        
        if (data[5].contains("ft")) {
            this.data[5] = this.data[5].replaceAll("[^\\d\\.]", "");
            this.data[5] = String.valueOf(Double.parseDouble(this.data[5]) / 3.28084);
            //System.out.println(this.data[5]);
        }

        this.height = Double.parseDouble(this.data[5].replaceAll("[^\\d\\.]", ""));
        Mountain.isValid(this.data);
    }

    public String getRawData() {
        return rawData;
    }

    public String[] getData() {
        return data;
    }

    public double getHeight(){
        return height;
    }

    public static void isValid(String[] data) {
        if(data.length != 6){
            throw new RuntimeException("Invalid number of fields");
        }
        if(data[0].isEmpty() || data[1].isEmpty() || data[2].isEmpty() || data[3].isEmpty() || data[4].isEmpty() || data[5].isEmpty()){
            throw new RuntimeException("Empty fields");
        }
        
        try {
            double lat = Double.parseDouble(data[3]);
            double lon = Double.parseDouble(data[4]);
            if(lat <= -90 || lat >= 90){
                throw new RuntimeException("Latitude out of range");
            }
            if(lon <= -180 || lon >= 180){
                throw new RuntimeException("Longitude out of range");
            }
        } catch (NumberFormatException e){
            throw new RuntimeException("Invalid latitude or longitude");
        }
        
        
    }



}
