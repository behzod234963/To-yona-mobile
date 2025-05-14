package com.mr.anonym.toyonamobile.ui.screens.detailsScreen.item

//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(10.dp),
//        contentAlignment = Alignment.BottomCenter
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(horizontal = 10.dp)
//        ) {
////                                    Event content
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(
//                    text = stringResource(R.string.event),
//                    color = secondaryColor,
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.SemiBold
//                )
//                Text(
//                    text = partyModel.type,
//                    color = secondaryColor,
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.SemiBold
//                )
//            }
//            Spacer(Modifier.height(5.dp))
//            HorizontalDivider()
//            Spacer(Modifier.height(10.dp))
////                                    Event date and time content
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(
//                    modifier = Modifier
//                        .fillMaxWidth(0.5f),
//                    text = stringResource(R.string.event_date_and_time),
//                    color = secondaryColor,
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.SemiBold
//                )
//                Text(
//                    text = partyModel.createdAt,
//                    color = secondaryColor,
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.SemiBold,
//                    textAlign = TextAlign.End
//                )
//            }
//            Spacer(Modifier.height(5.dp))
//            HorizontalDivider()
//            Spacer(Modifier.height(10.dp))
////                                    Requisites content
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                horizontalArrangement = Arrangement.Center
//            ) {
//                Text(
//                    text = stringResource(R.string.requisites),
//                    color = secondaryColor,
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.SemiBold
//                )
//            }
////                                    Cardholder content
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(
//                    modifier = Modifier
//                        .fillMaxWidth(0.5f),
//                    text = stringResource(R.string.card_holder_name),
//                    color = secondaryColor,
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.SemiBold
//                )
//                Text(
//                    text = "BEKHZOD KHUDAYBERGENOV",
//                    color = secondaryColor,
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.SemiBold,
//                    textAlign = TextAlign.End
//                )
//            }
//            Spacer(Modifier.height(5.dp))
//            HorizontalDivider()
//            Spacer(Modifier.height(10.dp))
////                                    Card number content
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(
//                    modifier = Modifier
//                        .fillMaxWidth(0.5f),
//                    text = stringResource(R.string.card_number),
//                    color = secondaryColor,
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.SemiBold
//                )
//                Text(
//                    text = receiverCardNumber.cardNumberFormatter(),
//                    color = secondaryColor,
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.SemiBold,
//                    textAlign = TextAlign.End
//                )
//            }
//            Spacer(Modifier.height(10.dp))
//            HorizontalDivider()
//            Spacer(Modifier.height(10.dp))
//        }
//        DetailsPriceFields(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(100.dp),
//            secondaryColor = secondaryColor,
//            fiverdColor = fiverdColor,
//            value = priceValue.value,
//            priceFieldError = priceFieldError.value,
//            onValueChange = { newValue ->
//                priceValue.value = newValue
//            }
//        ) {
//            if (
//                priceValue.value.isDigitsOnly() &&
//                priceValue.value.isNotEmpty() &&
//                priceValue.value.isNotBlank() &&
//                priceValue.value.toInt() >= 1000
//            ) {
//                showTransferDetails.value = true
//            } else {
//                priceFieldError.value = true
//            }
//        }
//    }