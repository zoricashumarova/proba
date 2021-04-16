import axios from "../custom-axios/axios";
const bookService={

    fetchBooks:()=>{
        return axios.get("/books");
    },
    fetchAuthors: () => {
        return axios.get("/authors")
    },
    fetchCategories: () => {
        return axios.get("/books/categories")
    },
    labelBookAsTaken: (id) => {
        return axios.post(`books/available-copies/${id}`)
    },

    addBook: (name, category, author, availableCopies) => {
        return axios.post("/books/add", {
            "name": name,
            "category": category,
            "author": author,
            "availableCopies":availableCopies
        });
    },
    editBook: (id,name, category, author, availableCopies) => {
        return axios.put(`/books/edit/${id}`, {
            "name": name,
            "category": category,
            "author": author,
            "availableCopies":availableCopies
        });
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);
    },
    getBook: (id) => {
        return axios.get(`books/${id}`)
    }
}
export default bookService;