import { ICountry } from "./country"
import { IColor } from "./color"
import { IAnimalType } from "./animalType"

export interface IPet {
    id?: number
    name: string
    code: string
    animalType: string
    furColor: string
    country: string
    addedBy?: string
}