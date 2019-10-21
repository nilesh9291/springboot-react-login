import React, { Component } from 'react'
import ApiService from "../../service/ApiService";

class UserDetailsComponent extends Component{

    constructor(props){
        super(props);
        this.state ={
            userName: '',
            password: '',
            firstName: '',
            lastName: '',
            age: '',
            salary: '',
            message: null
        }
        //this.saveUser = this.saveUser.bind(this);
    }

    // saveUser = (e) => {
    //     e.preventDefault();
    //     let user = {userName: this.state.userName, password: this.state.password, firstName: this.state.firstName, lastName: this.state.lastName, age: this.state.age, salary: this.state.salary};
    //     ApiService.addUser(user)
    //         .then(res => {
    //             this.setState({message : 'User added successfully.'});
    //             this.props.history.push('/users');
    //         });
    // }

    componentDidMount() {
        this.loadUser1();
    }

    loadUser1() {
        ApiService.fetchUserById(window.localStorage.getItem("userId"))
            .then((res) => {
                let user = res.data.result;
                this.setState({
                id: user.id,
                userName: user.userName,
                firstName: user.firstName,
                lastName: user.lastName,
                age: user.age,
                salary: user.salary,
                })
            });
    }

    onChange = (e) =>
        this.setState({ [e.target.name]: e.target.value });

    render() {
        return(
            <div>
                <h2 className="text-center">User Details</h2>
                <form>
                <div className="form-group">
                    <label>User Name:</label>{this.state.userName}
                </div>

                <div className="form-group">
                    <label>First Name:</label>{this.state.firstName}
                </div>

                <div className="form-group">
                    <label>Last Name:</label>{this.state.lastName}
                </div>

                <div className="form-group">
                    <label>Age:</label>{this.state.age}
                </div>

                <div className="form-group">
                    <label>Salary:</label>{this.state.salary}
                </div>
            </form>
    </div>
        );
    }
}

export default UserDetailsComponent;