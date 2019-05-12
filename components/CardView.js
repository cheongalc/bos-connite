import React from 'react'
import { StyleSheet, Text, View } from 'react-native'

export class CardView extends React.Component {
  constructor (props) {
    super(props)
    this.setState({})
  }
  render () {
    return (
      <View style={styles.container}>
        <Text>hi there i am a card</Text>
      </View>
    )
  }
}

const styles = StyleSheet.create({
  container: {
    backgroundColor: '#fff',
    shadowColor: 'black',
    shadowOpacity: 0.35,
    shadowRadius: 10
  }
})
