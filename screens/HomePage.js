import React from 'react'
import { StyleSheet, View, TouchableOpacity } from 'react-native'
import { CardView } from '../components/CardView'

export default class HomePage extends React.Component {
  constructor (props) {
    super(props)
    this.setState({})
  }
  static navigationOptions = {
    title: 'HomePage'
  }
  render () {
    return (
      <View>
        <TouchableOpacity
          style={styles.container}
          onPress={() => this.props.navigation.navigate('AccountInfoPage')} >
          <CardView />
        </TouchableOpacity>
      </View>
    )
  }
}

const styles = StyleSheet.create({
  container: {
    color: 'red'
  }
})
