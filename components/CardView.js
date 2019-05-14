import React from 'react'
import { StyleSheet, Text, View } from 'react-native'

export default class CardView extends React.Component {
  render () {
    return (
      <View style={styles.container}>

      </View>
    )
  }
}

const styles = StyleSheet.create({
  container: {
    color: '#fff',
    shadowColor: 'black',
    shadowOpacity: 0.35,
    shadowRadius: 10,
    padding: 10,
    margin: 10
  }
})
