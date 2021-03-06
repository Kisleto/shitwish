import React, {Component} from 'react';
import "./css/item.css"
import axios from 'axios';

class Item extends Component {

    constructor(props) {
        super(props);
        this.handleClick = this.handleClick.bind(this);
        this.state = {
            name: "",
            price: 0,
            imgURL: "",

        }
    }

    handleClick() {
        let shoppingCartURL = `http://localhost:60001/cart`;
        axios({
            method: 'post',
            url: shoppingCartURL,
            data: {
                name: this.props.name,
                price: this.props.price,
                imgURL: this.props.imgURL,
                quantity: this.props.quantity
            }
        })
            .then(function (response) {
                console.log(response)
            })
            .catch(function (error) {
                console.log(error)

            })
        this.setState({imgURL: ""})
    }


    render() {

            return (
            <div>

                <div className="containers">
                    <div className="row">
                        <div className="block span3">
                            <div className="product">
                                <img src={this.props.imgURL} width={411} height={236} mode='fit'/>
                                    <div className="buttons">
                                        <a className="buy btn btn-large btn-info cart" onClick={this.handleClick}
                                           href="#">
                                            Add to Cart</a>
                                    </div>
                            </div>
                            <div className="info">
                                <h4>{this.props.name}</h4>
                                <span className="description">{this.props.desc}</span>
                                <span className="price">{this.props.price} $</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            )

    }

}
export default Item