import React from "react";
import App from "../App";
import {BrowserRouter as Router, Link, Route, Switch} from 'react-router-dom'
import ShoppingCart from "./shopping-cart"
import "./css/header.css"


function AppRouter() {

        return (
            <Router>
                <div className="contain">
                    <h2 className="title">ShitWish</h2>
                    <nav className="navbar navbar-expand-lg navbar-light bg-light">
                        <ul className="navbar-nav mr-auto">
                            <li><Link to={'/'} className="nav-link"> Home </Link></li>
                            <li><Link to={'/shoppingCart'} className="nav-link">Shopping Cart</Link></li>
                        </ul>
                    </nav>
                    <hr/>
                    <Switch>
                        <Route exact path='/' component={App}/>
                        <Route path='/shoppingCart' component={ShoppingCart}/>
                    </Switch>
                </div>
            </Router>
        );

}

export default AppRouter;