# LOLEMON
## DESAROLLO DE APLICACIONES MULTIPLATAFORMA 
Desarrolladores:
Adrian Socas Toledo
Richard Hernández Afonso

### Analisis de los campeones
La mecánica principal de lolemon son los combates entre los personajes (campeones) del mismo. 
Cada uno de los personajes tiene diferentes estadísticas, y diferentes habilidades.
A su vez las diferentes habilidades tienen diferentes costes de energía, diferentes daños y diferentes tipos de daño, precisión etc... 
A continuación se explica las características de los campeones de lolemon.

Como se nombró anteriormente, todos los campeones tienen una lista de características importantes para la batalla, siendo estas las más importantes:
**Vida - Vida Total :** Siendo vida la vida actual del personaje que cambiará durante la batalla y vida total, valga la redundancia, la vida total del personaje.
**Energía - Energía Total :** AL igual que los atributos anteriores, la energía cambia durante la partida pero la energía total no. 
*Estos atributos tienen dos versiones puesto que unos se usan para la batalla y otros para calculos en el algoritmo interno del juego.*
**Velocidad :** Lolemon es un juego de combate por turnos, no existe la posibilidad de que un personaje ataque dos veces en el mismo turno, aún así la estadística de velocidad es necesaria para definir quién será el que haga el primer ataque al princiìo de la pelea pues esto puede dar ventaja.
**Defensa :** Estadística usada en el algoritmo interno de la pelea, por la cual el daño del enemigo es reducido usando esta estadística para los calculos.
**Recargo :** Los campeones en lolemon usan energía para lanzar las habilidades, tienen una estadística que es el recargo de la energía, que unida al algoritmo interno les permite recargar un porcentaje de la misma cada turno.
**Coste :** Como en lolemon los personajes se compran en la tienda, es necesario que tengan un valor.

Todos estos parámetros, quitando el coste, son importantes para la batalla en lolemon y entran en juego en el algoritmo. 


**Enlaces de interés**
**[Inicio](https://github.com/RichardHanks/Lolemon)**


 
