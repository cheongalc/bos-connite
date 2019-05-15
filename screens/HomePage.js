import React from 'react'
import { StyleSheet, View, TouchableOpacity } from 'react-native'
import CardView from '../components/CardView'
import UserHeader from '../components/UserHeader'
import ActivityItem from '../components/ActivityItem'

export default class HomePage extends React.Component {
  constructor (props) {
    super(props)
  }
  render () {
    return (
      <View style = { styles.mainFrame }>
          <View style = { styles.headerContainer }>
            <UserHeader
              style = { styles.header }
              avatarURL = 'https://yt3.ggpht.com/a/AGF-l7_L7vL6Oy81SONDWdR74cNoObVfod_NsG7OAg=s176-c-k-c0x00ffffff-no-rj-mo'
              userName = 'Justin Loh'
              userSub = 'A mildly gay chem teacher in HCI.' />
          </View>
          <ActivityItem activity = {{imageURL: tmpImageURL, title: 'Swimming', description: 'A healthy activity.', address: '123 Boon Lay Drive, #2-29'}}/>
      </View>
    )
  }
}

const styles = StyleSheet.create({
  mainFrame: {
    flex: 1,
    flexDirection: 'column',
    alignItems: 'center'
  },
  headerContainer: {
    width: '96%',
    height: '11%'
  },
  header: {
    width: '98%',
    height: '95%'
  }
})

const tmpImageURL = 'https://video.toggle.sg/image/11528076/16x9/947/533/374b839d634a69c047dc2135a05f494f/IR/singapore-2019-world-para-swimming-world-series-ep13-box-cover-msybcvod0190512007030018-20190513131710.jpg'
