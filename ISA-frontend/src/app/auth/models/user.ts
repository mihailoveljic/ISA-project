export interface User{
    email: string,
    roles: string[],
    jmbg: string,
    firstName: string,
    lastName: string,
    gender: string,
    phoneNumber: string,
    address: {
        country: string,
        city: string,
        street: string,
    },
    profession: string,
    professionInfo: string,
}