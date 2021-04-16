import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom'
import Books from '../Books/BookList/books'
import bookService from '../../repository/bookRepository';
import Header from '../Header/header'
import BookAdd from '../Books/BookAdd/bookAdd'
import BookEdit from '../Books/BookEdit/bookEdit'
import Categories from '../Categories/categories'
class App extends Component{
    constructor(props) {
        super(props);
        this.state={
            books:[],
            authors:[],
            categories:[],
            selectedBook: {}
        }
    }
    render() {
        return(
            <Router>
                <Header/>
                <main>
                    <div className="container">
                        <Route path={"/categories"} exact
                               render={() => <Categories categories={this.state.categories}/>}/>
                        <Route path={"/books/add"} exact render={() => <BookAdd categories={this.state.categories}
                                                                                authors={this.state.authors}
                                                                                onAddBook={this.addBook}/>}/>
                        <Route path={"/books/edit/:id"} exact
                               render={() => <BookEdit categories={this.state.categories}
                                                       authors={this.state.authors}
                                                       onEditBook={this.editBook}
                                                       book={this.state.selectedBook}/>}/>
                        <Route path={"/books"} exact render={()=><Books books={this.state.books}
                                                                        onDelete={this.deleteBook}
                                                                        onEdit={this.getBook}
                                                                        onLabel={this.labelBook}/>}/>
                        <Redirect to={"/books"}/>
                    </div>
                </main>
            </Router>
        )
    }
    componentDidMount() {
        this.loadBooks();
        this.loadAuthors();
        this.loadCategories();
    }
    loadBooks = () => {
        bookService.fetchBooks()
            .then((data)=>{
                this.setState({
                    books:data.data
                })
            });
    }
    loadAuthors = () => {
        bookService.fetchAuthors()
            .then((data)=>{
                this.setState({
                    authors:data.data
                })
            });
    }
    loadCategories = () => {
        bookService.fetchCategories()
            .then((data)=>{
                this.setState({
                    categories:data.data
                })
            });
    }
    getBook = (id) => {
        bookService.getBook(id)
            .then((data)=>{
                this.setState({
                    selectedBook: data.data
                })
            })
    }

    addBook = (name,category,author,availableCopies) => {
        bookService.addBook(name,category,author,availableCopies)
            .then(() => {
                this.loadBooks();
            })
    }
    editBook = (id,name,category,author,availableCopies) => {
        bookService.editBook(id,name,category,author,availableCopies)
            .then(() => {
                this.loadBooks();
            })
    }
    deleteBook = (id) => {
        bookService.deleteBook(id)
            .then(()=>{
                this.loadBooks();
            });
    }
    labelBook = (id) => {
        bookService.labelBookAsTaken(id)
            .then(()=>{
                this.loadBooks();
            })
    }

}
export default App;