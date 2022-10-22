export interface User {
  email: string;
  password: string;
}

export interface SignUpFormData extends User {
  name: string;
  passwordMatch?: string;
}