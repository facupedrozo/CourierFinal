package org.example.util;

public class CalcularDistancia {

        private static final double EARTH_RADIUS = 6371; // Radio de la Tierra en kilómetros

        public double calcularDistancia(double latitud1, double longitud1, double latitud2, double longitud2) {
            // Convertir las coordenadas a radianes
            double latitudRadianes1 = Math.toRadians(latitud1);
            double longitudRadianes1 = Math.toRadians(longitud1);
            double latitudRadianes2 = Math.toRadians(latitud2);
            double longitudRadianes2 = Math.toRadians(longitud2);

            // Calcular la diferencia entre las latitudes y longitudes
            double diferenciaLatitudes = latitudRadianes2 - latitudRadianes1;
            double diferenciaLongitudes = longitudRadianes2 - longitudRadianes1;

            // Aplicar la fórmula de Haversine
            double a = Math.pow(Math.sin(diferenciaLatitudes / 2), 2) +
                    Math.cos(latitudRadianes1) * Math.cos(latitudRadianes2) *
                            Math.pow(Math.sin(diferenciaLongitudes / 2), 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            double distancia = EARTH_RADIUS * c;

            return distancia;

        }
}
