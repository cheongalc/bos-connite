import React from 'react'
import { StyleSheet, Text, View, TouchableOpacity, Image, ImageBackground } from 'react-native'

export default class ActivityItem extends React.Component {
  render() {
    const activity = this.props.activity
    return (
      <View style = {styles.card}>
        <Image style = {styles.image} source = {{uri: activity.imageURL}} />
        <View style = {styles.vertContainer}>
          <Text style = {styles.actTitle}> {activity.title} </Text>
          <Text style = {styles.actDescription}> {activity.description} </Text>
          <Text style = {styles.actAddress}> {activity.address} </Text>
          <TouchableOpacity>
            <View style = {styles.horizontalContainer} >
              <Image source = {activity.navigationButtonURL} />
              <Text>Navigate</Text>
            </View>
          </TouchableOpacity>
        </View>
      </View>
    )
  }
}

const styles = StyleSheet.create({
  card: {
    width: '100%',
    height: '100%',
    paddingTop: '15%',
    backgroundColor: 'rgba(255, 255, 255, 0.01)',
    shadowColor: 'black',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.35,
    shadowRadius: 5,
    elevation: 5,
    zIndex: 2,
    flex: 1,
    flexDirection: 'row'
  },
  image: {
    width: '55%',
    height: '100%'
  },
  vertContainer: {
    flex: 1,
    flexDirection: 'column',
    width: '45%',
    height: '100%'
  },
  actTitle: {
    fontSize: 24,
    color: 'rgb(59, 59, 59)'
  },
  actDescription: {
    fontSize: 20,
    color: 'rgb(155, 160, 160)'
  },
  actAddress: {
    fontSize: 20,
    color: 'rgb(115, 147, 202)'
  }
})
