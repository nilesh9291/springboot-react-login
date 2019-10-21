import React, { Component } from 'react'
import ApiService from "../../service/ApiService";

class LoginUserComponent extends Component{

    constructor(props){
        super(props);
        this.state ={
            userName: '',
            password: '',
            message: null,
            user: null
        }
        this.loginUser = this.loginUser.bind(this);
    }

    loginUser = (e) => {
        e.preventDefault();
        let loginDto = {userName: this.state.userName, password: this.state.password};
        ApiService.loginUser(loginDto)
            .then(res => {
                this.setState({message : 'User logged in successfully.'});
                this.setState({user: res.data.result})
                //console.log(res.data.result.id);
                console.log(this.state.user);
                //this.props.history.push('/user-details');
                this.userDetails(this.state.user.id);
            });
    }

    userDetails(id) {
        window.localStorage.setItem("userId", id);
        this.props.history.push('/user-details');
    }

    onChange = (e) =>
        this.setState({ [e.target.name]: e.target.value });

    render() {
        return(
            <div>
                <h2 className="text-center">LoginUser</h2>
                <form>
                    <div className="form-group">
                        <label>User Name:</label>
                        <input type="text" placeholder="userName" name="userName" className="form-control" value={this.state.userName} onChange={this.onChange}/>
                    </div>

                    <div className="form-group">
                        <label>Password:</label>
                        <input type="password" placeholder="password" name="password" className="form-control" value={this.state.password} onChange={this.onChange}/>
                    </div>
                    <button className="btn btn-success" onClick={this.loginUser}>Login</button>
                </form>
            </div>
        );
    }
}

export default LoginUserComponent;