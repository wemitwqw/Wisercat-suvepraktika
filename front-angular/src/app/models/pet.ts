import { IAnimalCountries } from "./animalCountries"
import { IAnimalFurColors } from "./animalFurColors"
import { IAnimalTypes } from "./animalTypes"

export interface IPet {
    id?: number
    name: string
    code: string
    animalType: IAnimalTypes
    fur_color: IAnimalFurColors
    country: IAnimalCountries
}