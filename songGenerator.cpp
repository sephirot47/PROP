#include <iostream>
#include <string>
#include <time.h>
#include <stdlib.h>
#include <stdio.h>

std::string autors[] = {"JFons",
		     "Vector",
		     "Aina",
		     "Abraham",
		     "Melendi",
		     "Camela",
		     "Skrillex",
		     "Tomeu Penya",
		     "Bertri",
		     "DJ RFA"};

std::string titles[] = { "Cuando zarpa el hamor", "Mahjong beats", "Boca beats", "Paquito el chocolatero", "Double four", "Triple six", "Pentakill", "The Freak", "Sabata Rota", "All I want for Christmas is you"};

std::string estils[] = { "Flamenquillo", "Soul", "House", "Techno Punk", "Dubstep", "Folk", "Country", "Death metal", "Perroflautins", "Trash metal"};

int durations[] = {60, 120, 180, 240};
int years[] = {1600, 1700, 1800, 1900, 1950, 1960, 1970, 1980, 1990, 2000, 2010};
 
std::string  getRandomAuthor() { return autors[rand() % 10];}
std::string  getRandomTitle() { return titles[rand() % 10];}
std::string  getRandomStyle() { return estils[rand() % 10];}
std::string  getRandomStyleString() 
{ 
	int r = rand()%3;
	std::string result = "";
	if(r == 0) result = getRandomStyle() + ";-;-";
	else if(r == 1) result = getRandomStyle() + ";" + getRandomStyle() + ";-";
	else if(r == 2) result = getRandomStyle() + ";" + getRandomStyle() + ";" + getRandomStyle();
	return result;
}
int  getRandomDuration() { return durations[rand() % 4];}
int  getRandomYear() { return years[rand() % 11];}

int main(int argc, char **argv)
{
	srand(time(0));
	if(argc < 2) { std::cout << "Pon el numero de canciones" << std::endl; return -1;}
	int n = atoi(argv[1]);
	for(int i = 0; i < n; ++i)
	{
		std::cout << getRandomAuthor() << ";" << getRandomTitle() << "_" << i << ";" << getRandomYear() << ";" << getRandomStyleString() << ";" << getRandomDuration() << ";"; if(i < n-1) std::cout << std::endl;
	}
	return 0;
}
