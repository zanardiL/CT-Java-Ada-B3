import java.util.InputMismatchException;
import java.util.Scanner;

public class TemperatureConverter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        initialize();

        int inputUnit = selectTemperatureUnit("entrada");
        int outputUnit = selectTemperatureUnit("saida");

        double[] inputTemperatures = getTemperatures();
        averageTemperature(inputTemperatures, "entrada", inputUnit);

        double[] outputTemperatures = new double[inputTemperatures.length];

        if(inputUnit == 1) {
            outputTemperatures = convertFromCelsius(outputUnit, inputTemperatures);
        } else if (inputUnit == 2) {
            outputTemperatures = convertFromFahrenheit(outputUnit, inputTemperatures);
        } else {
            outputTemperatures = convertFromKelvin(outputUnit, inputTemperatures);
        }

        printTemp(inputUnit, outputUnit, inputTemperatures, outputTemperatures);
        averageTemperature(outputTemperatures, "saida", outputUnit);


    }
    private static void initialize() {
        System.out.println("...........................................................");
        System.out.println("\t\t\t\t CONVERSOR DE TEMPERATURAS");
        System.out.println("...........................................................");
        System.out.println("\t\tSeja bem vinda(o)");
    }
    private static int selectTemperatureUnit(String io) {
        Scanner sc = new Scanner(System.in);
        String unit = new String("");
        int tempUnit = 0;
        do {
            try {
                System.out.println("\t\tSelecione a temperatura de " + io);
                System.out.println("\t1 - CELSIUS                    ");
                System.out.println("\t2 - FAHRENHEIT                    ");
                System.out.println("\t3 - KELVIN                    ");
                System.out.println("\t4 - SAIR DA APLICACAO");
                System.out.println("...........................................................");

                tempUnit = sc.nextInt();
                System.out.println("...........................................................");
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("...........................................................");
                System.err.println("\tSelecao invalida!\n\tPor favor tente novamente.");
                System.out.println("...........................................................");
            }
        } while(tempUnit <= 0 || tempUnit > 4);

        if (tempUnit == 4) {
            System.out.println("\t\t\t\t----FIM DA APLICACAO----");
            System.out.println("...........................................................");
            System.exit(0);
        } else {
            return tempUnit;
        }
        return tempUnit;
    }
    private static double[] getTemperatures() {
        Scanner sc = new Scanner(System.in);
        int unit = 0;
        boolean continua = true;
        do {
            try{
                System.out.println("\tInforme a quantidade de temperaturas          ");
                System.out.println("\t\tque serao convertidas:          ");
                unit = sc.nextInt();
                System.out.println("...........................................................");
            }catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("...........................................................");
                System.err.println("\tEntrada invalida!\n\tPor favor tente novamente.");
                System.out.println("...........................................................");
            }
        } while(unit == 0);

        double[] temperatures = new double[unit];
        do{
            try {
                for (int i = 0; i < temperatures.length; i++) {
                    System.out.printf("\tInforme a temperatura %d: ", i + 1);
                    temperatures[i] = sc.nextDouble();
                }
                continua = false;
                System.out.println("...........................................................");
            }
                    catch (InputMismatchException e) {
                        sc.nextLine();
                        System.out.println("...........................................................");
                        System.err.println("\tEntrada invalida!\n\tPor favor tente novamente.");
                        System.out.println("...........................................................");
                        }
                    } while(continua);
            return temperatures;
        }


    private static void printTemp(int inputUnit, int outputUnit, double[] temperatures, double[] outputTemp) {
            String selectedInputUnit;
            String selectedOutputUnit;

            String outputUnitSymbol;
            String inputUnitSymbol;

            if(inputUnit == 1) {
                selectedInputUnit = "CELSIUS";
                inputUnitSymbol = " graus Celsius";
            } else if (inputUnit == 2) {
                selectedInputUnit = "FAHRENHEIT";
                inputUnitSymbol = " graus Fahrenheit";
            } else {
                selectedInputUnit = "KELVIN";
                inputUnitSymbol = " Kelvin";
            }
            if(outputUnit == 1) {
                selectedOutputUnit = "CELSIUS";
                outputUnitSymbol = " graus Celsius";
            } else if (outputUnit == 2) {
                selectedOutputUnit = "FAHRENHEIT";
                outputUnitSymbol = " graus Fahrenheit";
            } else {
                selectedOutputUnit = "KELVIN";
                outputUnitSymbol = " Kelvin";
            }

            System.out.println("\t\t\t" + selectedInputUnit + "\t\t\t->\t\t\t" + selectedOutputUnit );
            for(int i=0; i<outputTemp.length;i++) {
                System.out.println("\t" + temperatures[i] + inputUnitSymbol + "\t\t=\t\t" + outputTemp[i] + outputUnitSymbol );
            }
            System.out.println("...........................................................");

    }
    private static double[] convertFromCelsius(int outputTempUnit, double[] temperatures) {
        double[] outputTemp = new double[temperatures.length];
        switch (outputTempUnit){
            case 1: //FROM CELSIUS TO CELSIUS
                outputTemp = temperatures;
                break;
            case 2: //FROM CELSIUS TO FAHRENHEIT
                for(int i = 0; i<temperatures.length; i++) {
                    outputTemp[i] = temperatures[i] * 1.8 + 32;
                }
                break;
            case 3: //FROM CELSIUS TO KELVIN
                for(int i = 0; i<temperatures.length; i++) {
                    outputTemp[i] = (temperatures[i] + 273.15);
                }
                break;
            default:
                System.out.println("...........................................................");
        }
        return outputTemp;
    }

    private static double[] convertFromFahrenheit(int outputTempUnit, double[] temperatures) {
        double[] outputTemp = new double[temperatures.length];
        switch (outputTempUnit){
            case 1: //FROM FAHRENHEIT TO CELSIUS
               for(int i=0; i<temperatures.length; i++){
                   outputTemp[i] = (temperatures[i] - 32) * 5/9;
               }
               break;
            case 2: //FROM FAHRENHEIT TO FAHRENHEIT
                for(int i = 0; i<temperatures.length; i++) {
                    outputTemp[i] = temperatures[i];
                }
                break;
            case 3: //FROM FAHRENHEIT TO KELVIN
                for(int i = 0; i<temperatures.length; i++) {
                    outputTemp[i] = (temperatures[i] - 32) * 5/9 + 273.15;
                }
                break;
            default:
                System.out.println("...........................................................");
        }
        return outputTemp;
    }

    private static double[] convertFromKelvin(int outputTempUnit, double[] temperatures) {
        double[] outputTemp = new double[temperatures.length];
        switch (outputTempUnit){
            case 1: //FROM KELVIN TO CELSIUS
               for(int i=0; i<temperatures.length; i++){
                   outputTemp[i] = temperatures[i]-273.15;
               }
               break;
            case 2: //FROM KELVIN TO FAHRENHEIT
                for(int i = 0; i<temperatures.length; i++) {
                    outputTemp[i] = (temperatures[i] - 273.15) * 1.8 + 32;
                }
                break;
            case 3: //FROM KELVIN TO KELVIN
                for(int i = 0; i<temperatures.length; i++) {
                    outputTemp[i] = temperatures[i];
                }
                break;
            default:
                System.out.println("...........................................................");
        }

        return outputTemp;
    }

    private static void averageTemperature(double[] arrayTemperature, String io, int inputUnit){
        double average;
        double sum = 0;
        String unitSymbol;

        if(inputUnit == 1) {
            unitSymbol = " graus Celsius";
        } else if (inputUnit == 2) {
            unitSymbol = " graus Fahrenheit";
        } else {
            unitSymbol = " Kelvin";
        }
        for(int i = 0; i<arrayTemperature.length; i++) {
            sum = sum + arrayTemperature[i];
        }

        average = sum / arrayTemperature.length;
        System.out.println("Temperatura media de " + io +": " + average + unitSymbol);
        System.out.println("...........................................................");
        }
}