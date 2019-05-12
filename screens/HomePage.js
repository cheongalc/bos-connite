import React from 'react'
import { StyleSheet, View } from 'react-native'
import { CardView } from '../components/CardView'

export default class HomePage extends React.Component {
  constructor (props) {
    super(props)
    this.setState({})
  }
  render () {
    const {navigate} = this.props.navigation
    return (
      <View style={styles.container}>
        <CardView onPress={() => navigate('AccountInfoPage', {name: 'Test'})} />
      </View>
    )
  }
}

const styles = StyleSheet.create({
  container: {
    color: 'red'
  }
})
