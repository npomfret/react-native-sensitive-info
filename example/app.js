import React, { Component } from 'react';

import {
  AppRegistry,
  StyleSheet,
  Text,
  View
} from 'react-native';

import SInfo from 'react-native-sensitive-info';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});

class example extends Component {

  render() {
    SInfo.setItem('key1', 'value1', {
      sharedPreferencesName: 'mySharedPrefs',
      keychainService: 'myKeychain',
      encrypt: true });

    SInfo.setItem('key2', 'value2');

    SInfo.getItem('key1', {
      sharedPreferencesName: 'mySharedPrefs',
      keychainService: 'myKeychain',
      encrypt: true }).then((value) => {
        console.log(value); //value1
      });

    SInfo.getItem('key2').then((value) => {
      console.log(value); //value2
    });

    SInfo.getAllItems({
      sharedPreferencesName: 'mySharedPrefs',
      keychainService: 'myKeychain',
      encrypt: true }).then((values) => {
        console.log(values); //value1
      });

    SInfo.deleteItem('key2');
    SInfo.deleteItem('key1', {
      sharedPreferencesName: 'mySharedPrefs',
      keychainService: 'myKeychain',
      encrypt: true });

    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>
          Welcome to React Native!
        </Text>
        <Text style={styles.instructions}>
          To get started, edit the values above and be sure debugging mode is enabled to see our magic happening 🌟
        </Text>
        <Text style={styles.instructions}>
          Shake or press menu button for dev menu
        </Text>
      </View>
    );
  }
}

AppRegistry.registerComponent('example', () => example);
